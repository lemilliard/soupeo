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
            height: 300px;
            font-size 24px;
            background: white;
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
            display: flex;
            justify-content: space-around;
            align-items: center;
            width: 80%;
        }

        .response{
            transition: 1s;
            cursor: pointer;
            padding: 40px;
        }

        .response:hover{
            background: rgba(0,0,0,0.2);
        }


        </style>
    `);

    /* eslint-disable no-new */
    let vue = new Vue({
        el: "#injector",
        render: h => h(Injector)
    });
});