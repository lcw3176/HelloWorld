import{
    Motion
} from './Motion.js'

import{
    Ball
} from './Ball.js'

class App{
    constructor(id, webSocket) {
        this.id = id;
        this.ball = new Ball(this.id);
        this.motion = new Motion(id, webSocket);
    }


    onKeyDown(e){
        // 왼쪽 방향키
        if(e.key == "ArrowLeft"){
            this.motion.moveLeft();
        }

        // 오른쪽 방향키
        else if(e.key == "ArrowRight"){
            this.motion.moveRight();
        }

        else{
            this.motion.stopMove();
        }

    }


    onKeyUp(e){
        this.motion.stopMove();

    }
}


window.onload = function (){

    const uri = "ws://localhost:8080/auth/socket";
    let myId;
    let webSocket = new WebSocket(uri);
    let app;
    let men = {};

    document.getElementById("sendButton").addEventListener("click", function (){
        sendChat();
    });

    function sendChat(){
        if(webSocket != null){
            let value = document.getElementById("chat").value;

            if(value != null && value !== ""){
                let data = {
                    command:5,
                    content:value,
                    id:myId,
                }

                webSocket.send(JSON.stringify(data));

                document.getElementById("chat").value = "";
            }

        }
    }

    window.onbeforeunload = function (e) {

        let data = {
            command:4,
            content:"",
            id:myId,
        }

        webSocket.send(JSON.stringify(data));

        webSocket.close();
    };



    webSocket.onopen = function (e){

    }

    webSocket.onclose = function (e){

    }

    webSocket.onmessage = function (e){
        let data = JSON.parse(e.data);

        switch (data.command){
            case 0:
                myId = data.id;
                app = new App(myId, webSocket);
                break;

            case 1:
                men[data.id] = new Ball(data.id);
                break;

            case 2:
                if(data.id != myId){
                    men[data.id].setPosition(data.content);
                }
                break;

            case 3:
                if(data.id != myId){
                    men[data.id].setPosition(data.content);
                }

            case 4:
                document.getElementById(data.id + "tag").remove();
                document.getElementById(data.id).remove();

            case 5:
                if(data.id != myId){
                    men[data.id].setChat(data.content);
                } else{
                    app.ball.setChat(data.content);
                }

            default:
                break;
        }
    }

    webSocket.onerror = function (e){

    }




    window.onkeydown = function (e){

        if(e.key === "Enter"){
            sendChat();
        }

        app.onKeyDown(e);
    }

    window.onkeyup = function (e){
        app.onKeyUp(e);
    }
}
