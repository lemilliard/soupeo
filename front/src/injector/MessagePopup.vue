<template>
    <div class="popupBackground" v-if="title">
        <div class="popup">
            <div class="popupHelp">
                <span style="font-size: 44px;font-weight: 900;font-family: arial; margin:10px;">{{title}}</span>
                <br/>
                <span v-html="message"></span>
                <a :href="url">Cliquez ici</a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['title', 'message', 'url'],
    mounted(){
        if(!this.title && this.url){
           window.location.href = this.url;
        }

        if(this.title){
            var msg = new SpeechSynthesisUtterance(this.title);
            msg.lang = "fr-FR"
            window.speechSynthesis.speak(msg);
        }
    }
}
</script>

<style>

</style>
