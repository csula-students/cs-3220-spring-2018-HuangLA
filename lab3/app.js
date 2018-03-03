window.incrementalGame = {
	state: {
		counter: 0
		
	}
};

function increaseNum() {
    var counter = document.getElementById("dis-num").value;

    counter++;
    document.getElementById("dis-num").value = counter;
    window.incrementalGame.state.counter=counter;
}


class PubSub{
	constructor() {
		this.subscribers = [];
	}

	subscribe(fn){
		this.subscribers.push(fn);
	}
	publish (data){
		this.subscribers.forEach(subscriber => {
			subscriber(data);
		});
	}
}
