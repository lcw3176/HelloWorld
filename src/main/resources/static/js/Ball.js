export class Ball{
    constructor(id) {
        this.id = id;
        this.Born();
    }

    Born(){
        let childName = document.createElement("p");
        childName.setAttribute("class", "nameTag");
        childName.setAttribute("id", this.id + "tag");
        childName.innerText = this.id;

        let child = document.createElement("img");
        child.setAttribute("id", this.id);
        child.setAttribute("class", "character");
        child.src = "../img/ball1.png";
        let parent = document.getElementById("field");

        parent.appendChild(childName);
        parent.appendChild(child);
    }

    setPosition(position){

        let character = document.getElementById(this.id);
        character.style.transform = "rotate(" + position + "deg)"
        character.style.left = position + "px";

        let nameTag = document.getElementById(this.id + "tag");
        nameTag.style.left = position + "px";

    }


    setChat(text){
        let position = document.getElementById(this.id).style.left;

        let conversation = document.createElement("span");
        conversation.setAttribute("class", "conversation");
        conversation.innerText = text;
        conversation.style.left = position;

        console.log(position);
        let parent = document.getElementById("field");

        parent.appendChild(conversation);

        setTimeout(function (){
            conversation.remove();
        }, 2000);
    }

}
