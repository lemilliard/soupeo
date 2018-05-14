<template>
  <div class="home">
      <p>{{ $t('hello') }}</p>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
    }
  },
  mounted(){
    console.log(this);
  }
}
</script>

<i18n>
{
  "en": {
    "hello": "hello world!"
  },
  "ja": {
    "hello": "こんにちは、世界!"
  }
}
</i18n>

<style scoped>
</style>
