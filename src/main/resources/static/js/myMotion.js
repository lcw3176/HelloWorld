export class MyMotion {

    constructor(id, socketService) {
        this.id = id;
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
        this.maxSpeed = 15;
        this.velocityPlusValue = 1;
        this.xPosition = 0;
        this.nowVelocity = 0;
        this.stopVelocity = this.velocityPlusValue;
        this.isGoingFront = false;
        this.isStopping = false;
        this.moveId = null;
        //

        // 충돌 관련 변수
        this.pushVelocity = 20;
        this.left = "left";
        this.right = "right";
        this.collsionDirection = null;
        this.collisionStandard = 65;
        //

        // draw 관련 변수
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

    collision(othersPosition){
        let difference = this.xPosition - othersPosition;

        if(difference <=  this.collisionStandard && difference >  0){
            this.collsionDirection = this.right;

            if(this.moveId == null){
                this.nowVelocity = this.pushVelocity;
                this.moveLeft();
            } else{
                this.isGoingFront = true;
            }

            this.stopMove();
        }

        if(difference >=  -this.collisionStandard && difference <= 0){
            this.collsionDirection = this.left;

            if(this.moveId == null){
                this.nowVelocity = this.pushVelocity;
                this.moveRight();
            } else{
                this.isGoingFront = false;
            }

            this.stopMove();
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

        this.socketService.sendData(2, {x:this.xPosition, y:this.yPosition, collision:this.collsionDirection}, this.id);

        this.collsionDirection = null;
    }


}


