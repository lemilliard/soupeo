<template>
  <div class="injector">
    <popup />
    <spyware />
  </div>
</template>

<script>
import Popup from './Popup';
import Spyware from './Spyware';


export default {
  components: {
    Popup,
    Spyware
  },
  data() {
    return {};
  }
};
</script>
