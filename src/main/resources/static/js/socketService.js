export class SocketService {
    constructor(socket) {
        this.webSocket = socket;

    }

    sendClose(userId){
        let data = {
            command:3,
            content:"",
            id:userId,
        }

        this.webSocket.send(JSON.stringify(data));
        this.webSocket.close();
    }

    sendData(command, content, id){
        if(content !== ""){
            let data = {
                command:command,
                content:content,
                id:id,
            }

            this.webSocket.send(JSON.stringify(data));
        }
    }

}