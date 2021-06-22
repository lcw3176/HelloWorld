import{
    MyMotion
} from './myMotion.js'

import {
    Ball
} from './ball.js'

export class MyBall{
    constructor(id, socketService) {
        this.id = id;
        this.socketService = socketService;
        this.ball = new Ball(this.id, 0, 0, 100);
        this.motion = new MyMotion(id, socketService);
    }


    onKeyDown(e){

        // 왼쪽 방향키
        if(e.key === "ArrowLeft"){
            this.motion.moveLeft();
        }

        // 오른쪽 방향키
        else if(e.key === "ArrowRight"){
            this.motion.moveRight();
        }

        // 엔터키, 채팅전송
        else if(e.key === "Enter"){
            if(document.getElementById("chat").value.length <= 35){
                this.socketService.sendData(4, document.getElementById("chat").value, this.id);
            }
            document.getElementById("chat").value = "";
        }

        // 스페이스바
        else if(e.key === " "){
            this.motion.jump();
        }

    }


    onKeyUp(e){
        if(e.key === "ArrowLeft" || e.key === "ArrowRight"){
            this.motion.stopMove();
        }
    }
}