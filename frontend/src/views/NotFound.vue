<template>
  <section>
    <app-header />
    <one-column>
      <div class="not-found-message" slot="main">
        <h2>죄송합니다. 페이지를 사용할 수 없습니다.</h2>
        <p>
          클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.
          <router-link to="">Instagram으로 돌아가기</router-link>
        </p>
      </div>
    </one-column>
    <footer-layout></footer-layout>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Footer from '../components/common/Footer'

export default {
  components: {
    'app-header': Header,
    OneColumn,
    'footer-layout': Footer
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
</style>
