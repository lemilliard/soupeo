<template>
    <div class="popupBackground">
        <div class="popup">
            <div class="popupHelp" v-if="indexPage === 0">
                Vous avez besoin d'aide ?
                <div class="buttonContainer">
                    <div class="response" @click="infoAboutAssistant">Oui</div>
                    <div class="response" @click="closePopup">Non</div>            
                </div>
            </div>
            <div class="popupHelp" v-if="indexPage === 1">
                Notre assistant informatique peut comprendre votre problème. Cliqué en bas a droite de votre écran et formuler oralement votre question.
                <div class="buttonContainer">
                    <div class="response" @click="askQuestion">Ok</div>        
                </div>
            </div>

            <div class="popupHelp" v-if="indexPage === 2">
                Si vous ne souhaitez pas parler, vous pouvez aussi écrire votre question ci-dessous :
                <input type="text" v-model="question" placeholder="Ecrivez votre question" style="margin-top: 20px; background: rgba(0,0,0,0.05); border:none; font-size: 36px; width: 100%; height: 50px;"/>
                <div class="buttonContainer">
                    <div class="response" @click="sendQuestion">Envoyer</div>
                    <div class="response" @click="closePopup">Annuler</div>                                    
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    props: ['index'],
    data(){
        return{
            indexPage: 0,
            question: ""
        }
    },
    mounted(){
         document.documentElement.style.overflow = 'hidden';
    },
    methods: {
        closePopup(){
            document.documentElement.style.overflow = 'auto';
            this.indexPage = 0;
            this.$emit('removeHelp');
        },
        continueScenario(){
            this.indexPage++;
        },
        infoAboutAssistant(){
            this.$emit('toggleGlow');
            this.continueScenario();
        },
        askQuestion(){
            this.$emit('toggleGlow');
            this.continueScenario();            
        },
        sendQuestion(){
            console.log(this.question);
            axios.post("https://192.168.137.222:8443/help", {
                message: this.question,
                index: this.index
            }).then((data)=>{
                console.error('response from Server')
                console.log(data.data);
                this.$emit('injectPopup', data.data);
                this.$emit('removeHelp');
            }).catch(()=>{
                var msg = new SpeechSynthesisUtterance("Une erreur c'est produite");
                msg.lang = "fr-FR"
                window.speechSynthesis.speak(msg);
                this.$emit('removeHelp');                
            });
        }

    }
}
</script>

<style>
</style>
