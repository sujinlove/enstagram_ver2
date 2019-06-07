<template>
  <div class="popup"  v-show="showPopup" @click.self="$EventBus.$emit('showPopup')">
    <div class="popup-content">
      <slot></slot>
      <button @click="$EventBus.$emit('showPopup')">취소</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['PopupContent'],
  data () {
    return {
      showPopup: false
    }
  },
  created () {
    this.$EventBus.$on('showPopup', () => {
      this.showPopup = !this.showPopup
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../../assets/css/source/popup.scss';
</style>
