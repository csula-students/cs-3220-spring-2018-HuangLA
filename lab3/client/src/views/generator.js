export default function (store) {
 return class GeneratorComponent extends window.HTMLElement {
  constructor () {
   super();
   this.store = store;

   // TODO: render generator initial view
   this.generatorValue = this.store.state.generators;

   // TODO: subscribe to store on change event
   this.onStateChange = this.handleStateChange.bind(this);

   // TODO: add click event
   this.addEventListener('click', function(){
    this.store.dispatch({
     type: 'BUY_GENERATOR',
     payload: 'Grandma'
    });
   });

  }
 };
}