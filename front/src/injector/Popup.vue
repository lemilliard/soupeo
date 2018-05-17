<template>
    <div v-if="needHelp" class="popupBackground">
        <div class="popup">
            <div class="popupHelp" v-if="index === 0">
                Vous avez besoin d'aide ?
                <div class="buttonContainer">
                    <div class="response" @click="infoAboutAssistant">Oui</div>
                    <div class="response" @click="closePopup">Non</div>            
                </div>
            </div>
            <div class="popupHelp" v-if="index === 1">
                Notre assistant informatique peut comprendre votre problème. Cliqué en bas a droite de votre écran et formuler oralement votre question.
                <div class="buttonContainer">
                    <div class="response" @click="askQuestion">Ok</div>        
                </div>
            </div>

            <div class="popupHelp" v-if="index === 2">
                Si vous ne souhaitez pas parler, vous pouvez aussi écrire votre question ci-dessous :
                <input type="text" :bind="question" placeholder="Ecrivez votre question" style="margin-top: 20px; background: rgba(0,0,0,0.05); border:none; font-size: 36px; width: 100%; height: 50px;"/>
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
    data(){
        return{
            needHelp: true,
            index: 0,
            question: undefined
        }
    },
    mounted(){
         document.documentElement.style.overflow = 'hidden';
    },
    methods: {
        closePopup(){
            this.needHelp = false;
            document.documentElement.style.overflow = 'auto';
        },
        continueScenario(){
            this.index++;
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
            axios.post("https://192.168.1372:8443/help", {
                message: this.question,
                index: index
            }).then((data)=>{
                this.$emit('injectPopup', data);
            });
        }

    }
}
</script>

<style>
</style>
