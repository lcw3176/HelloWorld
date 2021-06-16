export class Motion{

    constructor(id, webSocket) {
        this.id = id;
        this.target = document.getElementById(this.id);
        this.webSocket = webSocket;
        this.defalutVelocity = 1;
        this.maxSpeed = 10;
        this.position = 0;
        this.nowVelocity = 0;
        this.stopVelocity = this.defalutVelocity;
        this.endOfWindow = 1580;
        this.startOfWindow = -100;
        this.isGoingFront = false;
        this.isStopping = false;
        this.stopId = null;
        this.left = "L";
        this.right = "R";
        this.direction = null;
    }

    moveLeft(){
        if(this.direction === this.right){
            this.stopMove();
            return;
        }
        this.direction = this.left;
        this.isGoingFront = false;

        if(this.nowVelocity <= this.maxSpeed){
            this.nowVelocity += this.defalutVelocity;
        }

        this.draw();
    }


    moveRight(){
        if(this.direction === this.left){
            this.stopMove();
            return;
        }

        this.direction = this.right;
        this.isGoingFront = true;

        if(this.nowVelocity <= this.maxSpeed){
            this.nowVelocity += this.defalutVelocity;
        }

        this.draw();
    }

    stopMove(){
        if(!this.isStopping){
            const timeOut = 30;
            this.isStopping = true;

            this.stopId = setInterval(this.stopCallback.bind(this), timeOut);
        }

    }

    stopCallback(){
        if(this.nowVelocity > 0){
            this.nowVelocity -= this.stopVelocity;
        } else{
            this.isStopping = false;
            this.direction = null;
            clearInterval(this.stopId);
        }

        this.draw();
    }

    draw(){

        if(this.position < this.startOfWindow){
            this.position = this.endOfWindow;
        }

        if(this.position > this.endOfWindow){
            this.position = this.startOfWindow;
        }


        if(this.isGoingFront){
            this.position += this.nowVelocity;
        } else{
            this.position -= this.nowVelocity;
        }


        this.target.style.transform = "rotate(" + this.position + "deg)"
        this.target.style.left = this.position + "px";

        let nameTag = document.getElementById(this.id + "tag");
        nameTag.style.left = this.position + "px";

        let conversation = document.getElementById(this.id + "conversation");
        if(conversation !== null){
            conversation.style.left = this.position + "px";
        }


        let data = {
            command:2,
            content:this.position,
            id:this.id,
        }

        this.webSocket.send(JSON.stringify(data));
    }

}


