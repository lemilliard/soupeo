<template>
    <div class="assistant" @click="startRecognition" style="width:200px;height:200px;position: fixed;bottom:0%;right:0%;background: rgb(200,200,200); z-index: 99999;pointer-events:auto;">
        {{text}}
    </div>
</template>

<script>
export default {
    data(){
        return {
            recognition: undefined,
            text: ""
        }
    },
    mounted(){
        try {
            var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
            this.recognition = new SpeechRecognition();
            this.recognition.lang =  "fr-FR";
            this.recognition.onstart = this.start;
            this.recognition.onspeechend = this.speechEnd;
            this.recognition.onerror = this.error;
            this.recognition.onresult = this.result;
            console.log(this.recognition);
        }
        catch(e) {
            console.error(e);
        }
    },
    methods: {
        start(){
            console.log("start");
        },
        result(event){
            var current = event.resultIndex;
            var transcript = event.results[current][0].transcript;
            this.text += transcript;
        },
        speechEnd(){
            this.text = "";
            console.log("stop");
        },
        error(event){
            if(event.error == 'no-speech') {
                console.log("no speech");
            };
        },
        startRecognition(){
            console.log("start recognition");
            this.recognition.start();
        }
    }
}
</script>

<style>

</style>
