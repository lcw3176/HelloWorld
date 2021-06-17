export class Motion {

    constructor(id, socketService) {
        this.id = id;
        this.target = document.getElementById(this.id);
        this.socketService = socketService;
        this.timeOut = 30;  // setInterval 시간

        // y축 움직임 관련 변수(jump 기능)
        this.defaultJump = 1;
        this.jumpId = null;
        this.maxJump = 10;
        this.yPosition = 0;
        this.isReadyToJumping = true;
        //
        
        // x축 움직임 관련 변수
        this.maxSpeed = 20;
        this.velocityPlusValue = 1;
        this.xPosition = 0;
        this.nowVelocity = 0;
        this.stopVelocity = this.velocityPlusValue;
        this.isGoingFront = false;
        this.isStopping = false;
        this.moveId = null;
        //

        // ui draw 관련 변수
        this.characterTopRate = 90;
        this.nameTagTopRate = 85;
        this.conversationTopRate = 75;
        this.endOfWindow = 1580;
        this.startOfWindow = -100;
        //
    }

    moveLeft(){
        if(this.moveId == null){
            this.isGoingFront = false;

            this.moveId = setInterval(this.xMoveProcess.bind(this), this.timeOut)
        }


    }


    moveRight(){
        if(this.moveId == null){
            this.isGoingFront = true;

            this.moveId = setInterval(this.xMoveProcess.bind(this), this.timeOut)
        }
    }

    xMoveProcess(){
        if(this.isStopping){
            if(this.nowVelocity > 0){
                this.nowVelocity -= this.stopVelocity;
            } else{
                clearInterval(this.moveId);
                this.isStopping = false;
                this.moveId = null;
            }
        } else{
            if(this.nowVelocity <= this.maxSpeed){
                this.nowVelocity += this.velocityPlusValue;
            }
        }

        this.draw();
    }

    stopMove(){
        if(!this.isStopping){
            this.isStopping = true;
        }

    }


    jump(){
        if(this.jumpId == null){
            this.jumpId = setInterval(this.yMoveProcess.bind(this), this.timeOut);
        }
    }

    yMoveProcess(){
        if(this.isReadyToJumping){
            this.yPosition += this.defaultJump;
        } else{
            this.yPosition -= this.defaultJump;
        }

        if(this.yPosition >= this.maxJump){
            this.isReadyToJumping = false;
        }

        if(this.yPosition <= 0){
            this.isReadyToJumping = true;
            this.yPosition = 0;
            clearInterval(this.jumpId);
            this.jumpId = null;
        }

        if(this.moveId == null && !this.isStopping){
            this.draw();
        }
    }

    draw(){
        if(this.xPosition < this.startOfWindow){
            this.xPosition = this.endOfWindow;
        }

        if(this.xPosition > this.endOfWindow){
            this.xPosition = this.startOfWindow;
        }


        if(this.isGoingFront){
            this.xPosition += this.nowVelocity;
        } else{
            this.xPosition -= this.nowVelocity;
        }

        this.target.style.transform = "rotate(" + this.xPosition * 5 + "deg)"
        this.target.style.top = this.characterTopRate - this.yPosition + "%";
        this.target.style.left = this.xPosition + "px";

        let nameTag = document.getElementById(this.id + "tag");
        nameTag.style.left = this.xPosition + "px";
        nameTag.style.top = this.nameTagTopRate - this.yPosition + "%";

        let conversation = document.getElementById(this.id + "conversation");

        if(conversation !== null){
            conversation.style.left = this.xPosition + "px";
            conversation.style.top = this.conversationTopRate - this.yPosition + "%";
        }

        this.socketService.sendData(2, {x:this.xPosition, y:this.yPosition}, this.id);
    }

}


