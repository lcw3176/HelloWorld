export class Ball {
    constructor(id, xPosition, yPosition, hp) {
        this.id = id;
        this.remainingHp = hp;
        this.characterTopRate = 90;
        this.nameTagTopRate = 85;
        this.conversationTopRate = 72;
        this.characterWidth = 65;
        this.hpTopRate = 80;
        this.halfOfeffectSize = 350;
        this.hpElement = null;
        this.nameElement = null;
        this.imgElement = null;
        this.conversationElement = null;
        this.born(xPosition, yPosition);
    }

    born(xPosition, yPosition){
        this.hpElement = document.createElement("progress");
        this.hpElement.setAttribute("class", "hp");
        this.hpElement.setAttribute("id", this.id + "hp");
        this.hpElement.value = this.remainingHp;
        this.hpElement.max = 100;
        this.hpElement.style.left = xPosition - 16 + "px";

        this.nameElement = document.createElement("p");
        this.nameElement.setAttribute("class", "nameTag");
        this.nameElement.setAttribute("id", this.id + "tag");
        this.nameElement.innerText = this.id;
        this.nameElement.style.left = xPosition + "px";
        this.nameElement.style.top = this.nameTagTopRate - yPosition + "%";

        this.imgElement = document.createElement("img");
        this.imgElement.setAttribute("id", this.id);
        this.imgElement.setAttribute("class", "character");
        this.imgElement.src = "../img/ball1.png";
        this.imgElement.style.left = xPosition + "px";
        this.imgElement.style.top = this.characterTopRate - yPosition + "%";

        let parent = document.getElementById("field");

        parent.appendChild(this.hpElement);
        parent.appendChild(this.nameElement);
        parent.appendChild(this.imgElement);
    }

    delete(){
        this.nameElement.remove();
        this.imgElement.remove();
        this.hpElement.remove();
        let checkExist = document.getElementById(this.id + "conversation");
        if(checkExist !== null){
            checkExist.remove();
        }

    }

    setPosition(xPosition, yPosition, collision){
        this.hpElement.style.left = xPosition + "px";
        this.hpElement.style.left = xPosition - 16 + "px";
        this.hpElement.style.top = this.hpTopRate - yPosition + "%";

        this.imgElement.style.transform = "rotate(" + xPosition + "deg)"
        this.imgElement.style.left = xPosition + "px";
        this.imgElement.style.top = this.characterTopRate - yPosition + "%";

        this.nameElement.style.left = xPosition + "px";
        this.nameElement.style.top = this.nameTagTopRate - yPosition + "%";

        if(this.conversationElement !== null){
            this.conversationElement.style.left = xPosition + "px";
            this.conversationElement.style.top = this.conversationTopRate - yPosition + "%";
        }

        if(collision !== null){
            this.hpElement.value -= 5;
            this.remainingHp = this.hpElement.value;
            let effect = document.createElement("img");
            effect.setAttribute("class", "collision");
            effect.src ="/img/collision.gif";

            if(collision === "left"){
                effect.style.left = xPosition - this.halfOfeffectSize + "px";
            }

            if(collision === "right"){
                effect.style.left = this.characterWidth + xPosition - this.halfOfeffectSize + "px";
            }

            let parent = document.getElementById("field");
            parent.appendChild(effect);

            setTimeout(function (){
                effect.remove();
            }, 500);

        }


    }


    setChat(text){

        if(this.conversationElement !== null){
            this.conversationElement.remove();
        }

        let position = this.imgElement.style.left;

        this.conversationElement = document.createElement("span");
        this.conversationElement.setAttribute("class", "conversation");
        this.conversationElement.setAttribute("id", this.id + "conversation");

        this.conversationElement.innerText = text;
        this.conversationElement.style.left = position;

        let parent = document.getElementById("field");

        parent.appendChild(this.conversationElement);

        (function (text){
            setTimeout(function (){
                if(this.conversationElement.innerText === text){
                    this.conversationElement.remove();
                    this.conversationElement = null;
                }
            }.bind(this), 2000);
        }.bind(this))(text)

    }


}
