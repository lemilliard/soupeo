<template>
  <div class="mainPopup">
    <p>Extension pole emploi </p>
    <input type="button" value="deconnexion" @click="deconnect"/>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted(){
  },
  methods: {
    deconnect(){
    }
  }
};
</script>

<style>
p {
  font-size: 20px;
}

.mainPopup{
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  width: 300px;
  height: 100px;
}
</style>
