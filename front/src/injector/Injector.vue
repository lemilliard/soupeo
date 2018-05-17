<template>
  <div class="injector">
    <popup v-if="needHelp && !stopPopup" @toggleGlow="assistantGlow = !assistantGlow" :index="index" @removeHelp="removeHelp" @injectPopup="injectPopup" />
    <spyware :index="index" @askForHelp="askForHelp" @resetIndex="index = 0" />
    <assistant :index="index" :glow="assistantGlow" @injectPopup="injectPopup" @startRecogntion="stopPopup = true" @stopRecognition="stopPopup = false"/>
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
      popupData: undefined,
      needHelp: false,
      stopPopup: false
    }
  },
  components: {
    Popup,
    Spyware,
    Assistant,
    MessagePopup
  },
  mounted(){
    console.error('Nouveau index / page');
    this.interval = setInterval(this.incrementTime, 1000);
    window.addEventListener("beforeunload", this.destroy);

    if(window.location.href == "https://www.google.fr/"){
      console.log("Send session start");
      axios.get("https://192.168.137.222:8443/session/start").then(()=>{
        axios.post("https://192.168.137.222:8443/parcours/open", {
          url: window.location.href
        }).then((index)=>{
          console.log("Session envoyé du server " + index.data);
          this.index = index.data;
        });
      });
    }else {
        axios.post("https://192.168.137.222:8443/parcours/open", {
          url: window.location.href
        }).then((index)=>{
          console.log("Session envoyé du server " + index.data);
          this.index = index.data;
        });
    }
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
      console.error('Reception dune popup ');
      if(data){
        console.error('data present');
        this.popupData = data;
      }
    },
    askForHelp(){
      console.error('ask for help');
      this.needHelp = true;
    },
    removeHelp(){
      console.error('remove help');      
      this.needHelp = false;
    }
  }
};
</script>
