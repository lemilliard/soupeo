<template>
  <div class="injector">
    <popup />
    <spyware />
    <assistant />
  </div>
</template>

<script>
import axios from 'axios';
import Popup from './Popup';
import Spyware from './Spyware';
import Assistant from './Assistant';


export default {
  data(){
    return{
      interval: undefined,
      time: 0
    }
  },
  components: {
    Popup,
    Spyware,
    Assistant
  },
  mounted(){
    this.interval = setInterval(this.incrementTime, 1000);
    window.addEventListener("beforeunload", this.destroy)
  },
  methods: {
    incrementTime(){
      this.time++;
    },
    destroy(event){
      console.error("eee");
    }
  }
};
</script>
