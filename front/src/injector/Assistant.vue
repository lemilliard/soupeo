<template>
    <div class="assistant" :class="{glow: glow && !isListening, listening: isListening && !text}" style="height:100px;position: fixed;bottom:0%;right:0%;margin: 10px; border-radius: 5px;background: rgba(0,0,0,0.6); z-index: 99999;pointer-events:auto;display:flex; align-items:center;justify-content:center;">
        <div @click="startRecognition" style="width:100px;height:100px; cursor: pointer;">
            <img height="100%" width="100%" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABSlSURBVHhezVsJVNXV1ud7a33rfWu99+qVoiYqDqGAs6ZGYiRmvdLXZL1KLcup0pxtstLqOc+agoLMiDIryFNMUZGcJ0AQQRTh3gvcgTsPgPj79j4XFBnvH62Xa511//z/5+yzf7+zzz777HN0cvqN/901m5+yGst8bUblXItOEWjVlR43V8gLTRUyg0kjqxaFnvmdjb5ZdGWBNn3ZvGqjyvfuXfNTv7F6j148AKcqi3JUpUm1waovv2LRKqpx10yvbRC/lVpUm1QgImCpkIvCz/yOv9Wva9GVVrMMllVlqRjFsv+w/4zGUmebSbXYZlJmolJnB0y/NJqg0W1T4bb1ZVWRbO7DWFbY4Q9DhEGR377SqFpeaVYpSVugSgca9TYBbokolmknoxKVJqWK+ltuMCja/1eJsOrKZ1WZ1aRZFe6Y1Y8cdHOEcF/cJ/dtNZbP+t1JMCiKPCrN6jQejbtWDcwaWRPg6Z22DBajARY91dGV09+lVNg6aov4m6YI/4p3EqYK9cl9sw5ERJqmtMDzdyHCpJVNqbJoDIClZYW15TCVZkOfFwuj7BxMyht2kDoVEVJBhSyGwTNoIoVJspjMRJiR/iayJJDBulRbKgxWfemU35QEMvl1gBU1Fg1o+WpRSYtBC0PRCchDnKGI7InS2GdQvm8MylNeg+rQBKgPvQF1yktQJ/tAlewL1cG3oE5fCF1uFMzqIkGG3TJatwrWhXVi3cy60vWPnITo6Og/WQ3lUUA1eXUHlOIR5dGkkS5LfIFI6IiySBdURHeEdq8zysI74FZgJ+T7u1DpgqLAzuJdRdTfUbHnCSgThkF7xR8Wg05YjLmiZbLrSKI4gkiohlVftgek8yMhgsHTeryPBbfu3WWktJZKBfTX46E68jGUsZ7QxXXF7ZBeOLqsLwI+GoofX/XCwlHe+HzE86LwM7/jb1ynaJcLtFF/hfb4NPt00CkdsgQmwqKV20kwsM6PgASan3tYYOumSOBNJhjlF6H8zwQa9fbQ0mgWBfdA1OzBAuT7vX3xnpsvZg72wSL6e8mLI0XhZ37H37jOwlGjqM0g3PR7DOYzM4UjvecvHJgSrKvQWavY+1BWYFKVrHd45Am8ofAwFFF9UBr6BHTxrkj7oZ8A907PMfh0qA+2TRqGI8v6IWd7b9wO7QV5RE9R+Jnf8Teu8+kzPqLNIu+ROLrEBZU3QlBpq4TVbCJrYMfZ+pQQcQORYNIUb2gTCQR+MjsVR+Y8z1X29IrdbjSXO0IT2wORnw3GZI/RmNJ3tDDta/5uREp3UVTRPVAe1QNlUT1F4Wd+V/ed63KbKf1G4wOPUQidPhSK6NehPusnHKPVSL7BARJYd8ZgUt/+QBIJNr3yaYq/zXfIs7Zq+qQQL2nlB/4JReiTqCDwQdOG4t2nfTHXaxROrvCEPsEVagLIq4EjhesaEl1xcrkn5j77PN7r7YPtb7mhcPXfcCvYF/qic7Ca9A6RwBgqTWqzXilzc5gEk7okg9fW1pY64XRo3dZd2wN5cDvoyexj5g0Sc3nR897CrPXxXSEP6wR5aEf65fIUFBHdWySidLedqNJAV5z7wY2mgjcmeoxBxNSBKFr3OAp+HgijIoecbevRJ2OojVkyHCLAoJJ9wmEm79JaHX0KbCxGPZSpE6GJehJn13rg4/4vYNaw55G1tQ+0MR3EtFAmvwHNkTnQHF1Aa/5bRIAr5OEuzZDgClkwLY/rn0LuKhcUb+2K00t7C5lTB76AY1+74+bqv0CWNAdWC/kEB/RkLIzJoLr9aYsk6IqLn6AlT3WHw1sHBPPyZCrPQ3lMf1rnu+CHV7wwsY8vDn3bD/o4Zwp8XoX+nD+MWftgzKTCv1n7oc1YhdLoIWQVznYiIrqJX2EpEX1xa5cPbgaOQaH/COSt647izc7Y93lfTPb0xdKxXshf54Ib2wbAWJ4Pi4NLJGOy6spUOl3xE82SYNTIljNTTcf2jS2CnZ++8BCNdCcc/r6vWMLWvDkC6j0doP7POwQ6EcYrcTBc2v1AMWYmwHAxAurDn6Aszgule/qhLGa4+Ft3LgiGyzHUNpZ+o1CRsQUlkW/g5oZOWDluOE0FXyTN8cTtTRRU5aXWOkQHrFVMhWoQxhVNEnBXp3vCqlNoqyXs6sT8zwmDMrIdVr0+Qnj9jOU9oU8ZCcMVBhDdCPw9Mi7vFVZhoF/9hWCqF1VLGLeJshciwJgVB1NOMlTxE3B4vis+7OeL5a+MwI017aC5vJuWRto7OGKtVIex0fKo0+tlTzYigZzFIjH6DgoTDpDWfvO1Xcje1EkEMt+NfQ6K8M7QZfxgB9dg5Jv+m4HupbKn2fpsDfqzO1Cw8WksGT0CM4f44PTXZAGXdtljAwk6M0bCuvgBAsg2/sekKcm9SwkHKcLYAioLQ3HwS1ex7AVPGwBDEhUy75YAOUZMw2kTh/LIF7DjXU+87+6L+BndYMoOkWQBjI0xEtZrhPn+XsGikT/HH6wSMzm8AlTL9iFsmgfeIwIOfd0LxtR/0OjTHHdo9B8E2VIbU/Y+qBP/hcRPemAiERDwrjusN/bXxgMO+IBaK2GMdzlrVSEfec8K6I8NnFiQMvpiCtAevlp1msLX4Zjs7oNTK1xhPDpRgvlLI0BzYAZ+WdAVH/b1waa3hsOmOE2xgIMrVr1pwlgtGsXGewSY1LJszrdJJoCyPNWGQmz+YDw+8hyJ7M1dYMuYSg7QPv+NV/bAnBUtiilzb+0zz3d2cg3A0ztzdjSsV2NhoV9Ldox45nZCFi2h+sOzcPpbF3zcdyQ2vD+e0o+FtAxKT7oyVpoGVwUBpvLyjmQW1ZVtyt7ShqNaB/85n2Ni70HY9LY7zgXMQeW1RAG24mwobh1Yj2t7fkDO7qW4Hrsc8iPbYCSwpsz7To9BcilO3YLLod/gzI4FJGcRcqN/hOrXIEFGVV4izvjPw5px7phEffnN/lz0LXXQuD5jpdXgjklZ1MnJqJL5ci5esiBaV6sol08ZWix4fQJGdeqG17oOQcKPtJjc3AfZ4a045zcflwK/IAJ+xPWYf+NqxPfiXRaBVJ8KEhbCwHXnw3F6+zyk/vsDnNo2FxeDv8L5wMU4tm4GDv00GfmJq4TMxJ8W43XXIdSXK+a/NgGVRiXlAylh0kp2qilsjNmolo1xMqpL5nEOXyoB4jCDNhpLp8/AiHYuGNOjD951G4zklYtQTXnAK0Ff4QYpzuaOAspNFB2AjUy64kwoMomAvL0/CfAmIuHU9rlI3/QZkRKMypxYoDgFKNwvCGIrSFs3ndruQcrqL2mfMVj09Wx7FyydNkPowLpI1Z8xE/b5TsReYFssAHdNiN2xHUP/2h4vkkJcBAErFqKmIAG6CxECQGVODI7vXokdy+dAdSYMVjJn/cVIMeps2sVkKTzy6tMEPjcOqrNhoi63YTLqLKQmPx4HVn0hCKjrbwj1Heu/jU6TpMUCImEirF4W6ERBwQmpDpBPbIg9TBrpA28yx7E93YVSrFzyysVEQLyY51byA2U0qp6eHnD601+wZemnILMQ37gwAQX71iBt7TRBCo/61mWfibqeHu4o/TVYyOC6DQngPnkqTHzOR+gi9QTK7ghlJ5iAm3wuJ8WE7tIZXlZGGnxcegrgDxLwhSCAPbeZLICBTXrzZfTo2Qspu5bhTt79vQETcGP/WkEAW0zN9QRRh+tOfPMl0ZZlsKymCOC+fVx6IJN0YZ2kYGDMJo280IlPZqXOIVTrkZ6cgJEdurZIgCCBvL2WwN0+HgAbLW/1l7+GBPBIc53bxwNFG25bV785Ap5z7oLjieQ37lDeUEJIzJgJu56d4B0pDcX8IQLOpB6Ad8durRJQR0JD8Py+KQL4PdetD74lC2AdTh1KEjpJwcE5Q9oZVvMUqJHSkOtW09Iju56FV/v0x+huTzc7BVoLh+t8wJHVH9udZlMBUm3A1JQFcN+sA+vCOknFQctgDRNgkjoFRIaFTG7ZjJkY9ngHvNTLo54TvO8DWiOAg6VbKRuQsXW2ZAK4z2GPd8QyWoZZFz4PkEKAmAIamZEJKKqmYEZKY657x1qBWzmX8ErvfsIXsCO0rwKOE8AEsaPT0+i3RlZ9C+C+uM9/9KbsEenAukjVnzET9lsUByjS+WaGVAEi2VhjxIVjqRjnMRBDH3PGhG59kbTCvgy2Buh+YoTi/BZMv74TTF6xiM4LBsC7c3eM9xyI82mHhA6OJG8b4mPM1C7diT7saksgJAQKEgwoyc/G6vnzMX34C0jiOICCFocJcHDbXENHbSlrvsKUAV54p99QFFw6TVt6CmbaEAbXBUJGjTzIiY6P5rclFK5jlNm/Y6ELCzQSFaXFUGYdgfFiWJME8C7PlhsLG0WHTRb6xnWaIo9lWorPIW7jFnzz8mviThElcCVbbp3ejJlWggW0DMrGtNkCGqy7NhPF5eW5MFxoTAAvaxfDlyNty1c46fdtkyVt81dUZ0WjJVAQQjJhKsKqydPgN2+h5HW/0RQQmyH5i068JeStYdu2ww08L5/nq29TPoBGkdNi9czbejUa2dFr4T/1HVF2ffo+gmpL4Cfvwe/jt8G/1xI2NLICPcmquZmC3PTD+NBtEDLTUlHTBr9VR0LddtisUtmv4ZEZX5WaD2zWaeqVMBYcg+F8SCNTZrO/cWAr9v84G+FzP0TwrEkInj0JkQs+Quq6RZCn7RRToz5x+ou7UZVLhOYdxuIx/8T6qTMF+NaP6ptfFmvzgjn3M0Ia2aa2pMSaJEFYQZFIZxsvRTYiwXo1RmyBGWxB0mYiZAvK0ncJ4DX5CbR1plK4T+wizVl76TkRZSeD8d0r4zHPeyzUxfmoIp8jddWqX5+x0qBvvkeAoULufbdKLzkp2pwSfGZXejkF2jNBAmyjgxHeCRI4nhZV1+IoV5BE2+RoFBzchoR1i7Fy2ru4HLse2vMRSKYt8MwBI7Bk3ASU3shBTZVYvtpMgD0pquekqPc9AjhFTAeieY9qGtiMatz8NQ45iauhof19w7i+jhB+r6YEyc4vZmCGr4+IKJ917oYhf++MtwcMwULvMZg5cDh2L/uGbnyU446t4qHA84AxRqO6+PoDaXFmwqIpXSzlWKwlE7QSAYqLB3AlZjmyE9dBkR4oLKFhwMO5gb0r5sPz/5zh/VQP+Lr2FhHlmO598HznXgj9ciYyY1ZAn3+UzJ6CtYcY+XtxCx/8auRfNDoZMsjl7egys64tm4qGZFj0Kmiu/oKi1K1EwHpciVuDwtSfhUnzqNcRcZfm/GG/7zC8XVcC3RujCLRXh+6iTPF+DrnJG6E45keyDlMKXvpmp6FejI32ADqDQd6uyfNB2h6ufBRWwMqqs1NJ+R0oIWeXm7RRkJCzfwPkJwJo4xMpSLCRQ1RTmmyylxeNvhum+nhj1fR/IW71fEFcKTlHxTF/IeuhCRCHo1Vs/quaPR3mo2O6bKh26GZIc8kHcjJWkxZqsgD50e0EIghySnBcT9mMzIS1yIxfKwgpOLgVNw9vRwmRdDl2DY4Ffo9rNOK3fvlZvCs7GWRvm7ZdyGKZ9lum0nZ9dfUZE12fUzd5MFqfEYOq+DPHL0jYr6XV6Gl7bCKGTQrKsshhKM2H8kICKb8DihO7BBAuhYe2CZ+QVUsEk5EZv4YI2YC8A5uEhdxMtZPGhdvKiQyWxTIphSX64L64T0e3wHUXJCgJ+plDt0QIxK8tXZGp0pEiRgJspn2AXoZCmRzhlzWIzKAw+Gw4jXgAKR4gANQvDIqnxI2DPwvAbAlc8g5QPHCIRp6+1QGv345lsUyWvZv6CLukEX1y36wD68I6NWUddVdk6PeUQ+C5kl4l60338i3VtQGHlS8fGqjUdqZWynH8eilWn1TizfgKeATp8fh2M9x2qpF3NATadP9G4OsA1Y2umN+1BN1/Zx/1pkoFybxOst12qvAY9eVOfXLfq9OVQhcV6VQ3KKwr62zPXqn5kpTFRpgcJkCEx+rbH/IVM1hpTtOVtKwiBYLPl2NGihrDI3TovMOAJ7cb0WmHEd0DDXh6lwHOOysxfW8uLBnboUxvbAHNgWvtPctimTP25og+uC/uk/tmHViX4eE6oRvryLqyzqx77TW5tl2iPnejZOuqTGDsXi16BurRjjpz9jOiW4ARvUgJVqRhYQW/jT8PMylckb6TvH7TI9oaaDH/qbAMBv8dyawD37BP1oV1Yt1YR9Z1bLQWrPvZ/JKfJY38g5Xh1MHfkNSNLsv2qB3lpkDXf9dzlxHtiYSZNFq3jofA9uu2NlkDj7otY5uQMTM6R8hk2a31z99ZV9a5g58h2cnpIf+f0dKQtD8PDNP/0odS7450XleHFR4WosTOpDSUHKcTHgJjPOlHV+IDUHYiEKX1LIOf+R1/4zpcl9sEJKcJGSxLSt+sK+l8ZGla2p8fYvTvN7WTYNjvziQEmRxSxo1GokugWSjvFVqOJfEXkHwwCdfSIgRY3ckdMJz0F4Wf+R1/O0B1lpC5cxtuyzJYlkMEkG6s48BQQxLr/EjA1xcyKEzr7x5DJITSiNA8c0gpUr5rLRGdAywYEKzBS+EleC+qAFPJYXLhZ37H37gOA+c2jsoXupBOrNugML3/Qxp9y7w9E14xyz3canOjU2+HFawdQXZWroEmuBDITgFWdNhpE4Wf+R1/a865ttQX6+IebrMNDVXPfuSj3pTA0aGKIf0jLKfFlAirkmQNUklrtj6POvXNo94/3HKGdfpdwNd1gug3//RMhH6xR5hF04enRYhNskW0mQzqi/vkvlkH1uV3BV+/szciSlwGR5o2kjI6QUT4HSLCseVKGgEkk2TbgZv1g8NNm14PLujyXwPesOPxYfKuQyINS/uGma+705zsEw24MRlBEpxZQ29PbVkGy2KZLHtIpGnp+Bh51z8M8IaKxFy9+r/eUbqXB0aY/DxDjHl9Qihup9ymIIQdZ1g1egVbHghqOMDhd/yN6wjA3IbasgyWxTJZ9h8WeHOKUUja3StSP25wmG7JgFBD5IBQ/fn+ofrS/sFa9KYNDRd+7heiK+NvXIfrekWaxnHb3xrw/wO6l5YH6YN71gAAAABJRU5ErkJggg==" alt="" />
        </div>
        <span v-if="text" style="margin: 0px 20px 0px 20px; font-size: 36px; color: white;">
            {{text}}
        </span>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    props: ['glow'],
    data(){
        return {
            recognition: undefined,
            text: "",
            timeout: undefined,
            isListening: false
        }
    },
    mounted(){
        try {
            var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
            this.recognition = new SpeechRecognition();
            this.recognition.lang =  "fr-FR";
            this.recognition.onstart = this.start;
            this.recognition.onerror = this.error;
            this.recognition.onresult = this.result;
        }
        catch(e) {
            console.error(e);
        }
    },
    methods: {
        start(){
            this.isListening = true;
        },
        result(event){
            var current = event.resultIndex;
            var transcript = event.results[current][0].transcript;
            this.text += transcript;
            setTimeout(()=>{    
                axios.post("https://192.168.137.222:8443/help", {
                    message: this.text,
                    index: index
                }).then((data)=>{
                    this.$emit('injectPopup', data);
                    this.isListening = false; 
                    this.text = "";
                });               
            }, 5000);
        },
        error(event){
            if(event.error == 'no-speech') {
                console.log("no speech");
                this.isListening = false;
            };
        },
        startRecognition(){
            if(!isListening){
                console.log("start recognition");
                this.recognition.start();
                this.isListening = true;
            }
        },
    }
}
</script>

<style>

</style>
