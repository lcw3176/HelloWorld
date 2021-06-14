let nowKeyInput = null;

window.onkeydown = function (e){

    let target = document.getElementById("chochyumsan");

    if(nowKeyInput == null || nowKeyInput == e.key){
        console.log('방향키 인식');
        console.log(e.key);

        // 왼쪽 방향키
        if(e.key == "ArrowLeft"){
            moveLeft(target)
        }

        // 오른쪽 방향키
        if(e.key == "ArrowRight"){
            moveRight(target)
        }

        nowKeyInput = e.key;
    } else{
        nowKeyInput = null;
        stopMotion(target);
    }


}

window.onkeyup = function (e){
    if(nowKeyInput == e.key){
        let target = document.getElementById("chochyumsan");
        nowKeyInput = null;
        stopMotion(target);
    }

}