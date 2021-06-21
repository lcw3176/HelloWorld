import {SocketService} from "./socketService.js"
import {MyBall} from "./myBall.js";
import {Ball} from "./ball.js";


window.onload = function (){

    const uri = "ws://localhost:8080/auth/socket";
    let webSocket = new WebSocket(uri);
    let socketService = new SocketService(webSocket);
    let myId;
    let myBall;
    let others = {};

    webSocket.onmessage = function (e){
        let data = JSON.parse(e.data);
        // 0: userBorn, 1: alertExistUserToNewUser, 2: Move, 3: Close, 4: Chat
        switch (data.command){
            case 0:
                if(myId === undefined){
                    myId = data.id;
                    myBall = new MyBall(myId, socketService);
                } else {
                    others[data.id] = new Ball(data.id, 0, 0, 100);
                    socketService.sendData(1, {x:myBall.motion.xPosition, y:myBall.motion.yPosition, hp:myBall.ball.remainingHp}, myId);
                }

                break;

            case 1:
                if(!Object.keys(others).includes(data.id) && myId !== data.id){
                    others[data.id] = new Ball(data.id, data.content.x, data.content.y, data.content.hp);
                }
                break;

            case 2:
                if(data.id !== myId){
                    if(Math.abs(myBall.motion.xPosition - data.content.x) <= 65){
                        myBall.motion.collision(data.content.x);
                    }
                    others[data.id].setPosition(data.content.x, data.content.y, data.content.collision);
                } else{
                    myBall.ball.setPosition(data.content.x, data.content.y, data.content.collision);
                }
                break;

            case 3:
                others[data.id].delete();
                delete others[data.id];
                break;

            case 4:
                if(data.id !== myId){
                    others[data.id].setChat(data.content);
                } else{
                    myBall.ball.setChat(data.content);
                }
                break;

            default:
                break;
        }
    }

    window.onkeydown = function (e){
        myBall.onKeyDown(e);
    }

    window.onkeyup = function (e){
        myBall.onKeyUp(e);
    }

    window.onbeforeunload = function (e) {
        socketService.sendClose(myId);
    }

    document.getElementById("sendButton").addEventListener("click", function (){
        socketService.sendData(4, document.getElementById("chat").value, myId);
        document.getElementById("chat").value = "";
    })

}
