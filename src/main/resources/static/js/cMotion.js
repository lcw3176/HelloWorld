const velocity = 0.5;
const maxSpeed = 30;
let status = 0;
let nowVelocity = 0;
let stopVelocity = velocity;
const endOfWindow = 1580;
const startOfWindow = -100;
let isGoingFront = false;

function moveLeft(target){
    if(isStopping){
        return;
    }

    isGoingFront = false;

    if(status <= startOfWindow){
        status = endOfWindow;
    }

    if(nowVelocity <= maxSpeed){
        nowVelocity += velocity;
    }

    status -= nowVelocity;
    target.style.transform = "rotate(" + status + "deg)"
    target.style.left = status + "px";

}

function moveRight(target){
    if(isStopping){
        return;
    }

    isGoingFront = true;

    if(status >= endOfWindow){
        status = startOfWindow;
    }

    if(nowVelocity <= maxSpeed){
        nowVelocity += velocity;
    }

    status += nowVelocity;
    target.style.transform = "rotate(" + status + "deg)"
    target.style.left = status + "px";

}

const timeOut = 30;
let isStopping = false;

function stopMotion(target){
    isStopping = true;
    if(isGoingFront){
        let stopId = setInterval(function (){
            stopRight(target, stopId);
        }, timeOut);
    } else{
        let stopId = setInterval(function (){
            stopLeft(target, stopId);
        }, timeOut);
    }

}
function stopLeft(target, stopId){
    if(status >= endOfWindow){
        status = startOfWindow;
    }

    if(nowVelocity > 0){
        nowVelocity -= stopVelocity;
    }

    status -= nowVelocity;
    target.style.transform = "rotate(" + status + "deg)"
    target.style.left = status + "px";

    if(nowVelocity <= 0){
        isStopping = false;
        clearInterval(stopId);
    }
}

function stopRight(target, stopId){
    if(status >= endOfWindow){
        status = startOfWindow;
    }

    if(nowVelocity > 0){
        nowVelocity -= stopVelocity;
    }

    status += nowVelocity;
    target.style.transform = "rotate(" + status + "deg)"
    target.style.left = status + "px";

    if(nowVelocity <= 0){
        isStopping = false;
        clearInterval(stopId);
    }
}
