<template>
  <div class="page-wrapper">
    <main class="page-layout-2columns-right">
      <div class="columns">
        <div class="column main">
          <slot name="main"></slot>
        </div>
        <div class="column sidebar">
          <slot name="sidebar"></slot>
        </div>
      </div>
    </main>
    <slot name="popup">
      <popup></popup>
    </slot>
  </div>
</template>

<script>
import Popup from './Popup'

export default {
  components: {
    Popup
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
