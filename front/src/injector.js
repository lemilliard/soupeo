import Vue from "vue";
import Injector from "./injector/Injector";

$(function() {
    $("body").append(`
        <div id="injector"></div>
        <style>
        
        .injector{
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            pointer-events: none;
        }

        .popupBackground{
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.6);
            position: absolute;
            display: flex;
            align-items: center;
            justify-content: center;
            pointer-events: auto;
            z-index: 9999;
        }

        .popup{
            width: 500px;
            font-size 24px;
            background: white;
            padding: 50px;
        }

        .popupHelp{
            width: 100%;
            height: 100%;
            text-align: center;
            pointer-events: auto;
            display: flex;
            align-items: center;
            justify-content: space-around;
            flex-direction: column;
            font-size: 36px;
        }

        .buttonContainer{
            margin-top: 40px;
            display: flex;
            justify-content: space-around;
            align-items: center;
            width: 80%;
        }

        .response{
            transition: 1s;
            cursor: pointer;
            padding: 40px;
            border: 2px solid rgba(0,0,0,0.2);
            margin-right: 5px;
            margin-left: 5px;
        }

        .response:hover{
            background: rgba(0,0,0,0.2);
        }

        .listening{
            animation: scale 1s linear infinite alternate;
        }

        @keyframes scale {
            0% {
                transform: scale(1);             
            }
            100% {
                transform: scale(1.15);
            }
          }

        .glow{
            margin: 20px;
            box-shadow: 0px 0px 10px 5px red;
            animation: scaleGlow 1s linear infinite alternate;
        }
        
        @keyframes scaleGlow {
            0% {
                transform: scale(1);             
            }
            100% {
                transform: scale(1.3);
            }
          }
          
          


        </style>
    `);

    /* eslint-disable no-new */
    let vue = new Vue({
        el: "#injector",
        render: h => h(Injector)
    });
});