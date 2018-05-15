<template>
    <div v-if="needHelp" class="popupBackground">
        <div class="popup">
            <div class="popupHelp" v-if="!hotesseCalled">
                Vous avez besoin d'aide ?
                <div class="buttonContainer">
                    <div class="response" @click="callForHelp">Oui</div>
                    <div class="response" @click="closePopup">Non</div>            
                </div>
            </div>
            <div class="popupHelp" v-else>
                Une hotesse de l'air à était appelé.
                <div class="buttonContainer">
                    <div class="response" @click="closePopup">Ok</div>        
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            needHelp: true,
            hotesseCalled: false,
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
        callForHelp(){
            this.hotesseCalled = true;
        }
    }
}
</script>

<style>
</style>
