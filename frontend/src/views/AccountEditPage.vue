<template>
  <section>
    <app-header />
    <one-column>
      <div class="account-edit container">
        <div class="profile">
          <div class="my-pic">
            <div class="my-pic-inner">
              <img :src="this.$store.state.user.profile" :alt="this.$store.state.user + '님의 프로필 사진'" />
            </div>
          </div>
          <div class="my-name" @click="testm"><span>{{ this.$store.state.user.id }}</span></div>
        </div>
        <form class="form" name="form">
          <fieldset class="fieldset">
            <div class="field name">
              <label for="account-name" class="label">이름</label>
              <input id="account-name" name="name" type="text" class="control" v-model="$store.state.user.name"/>
            </div>
            <div class="field id">
              <label for="account-id" class="label">사용자 이름</label>
              <input id="account-id" name="id" type="text" class="control" maxlength="30" v-model="$store.state.user.id"/>
            </div>
            <div class="field des">
              <label for="account-des" class="label">소개</label>
              <textarea id="account-des" name="des" class="control" maxlength="30" v-model="$store.state.user.des"/>
            </div>
            <div class="field passwd">
              <label for="account-passwd" class="label">비밀번호</label>
              <input id="account-passwd" name="passwd" type="password" class="control" v-model="$store.state.user.passwd"/>
            </div>
            <div class="field email">
              <label for="account-email" class="label">이메일 주소</label>
              <input id="account-email" name="email" type="email" class="control" v-model="$store.state.user.email"/>
            </div>
            <div class="field btn">
              <button name="account-edit-btn" type="button" @click="editAccount" class="action primary">제출</button>
            </div>
          </fieldset>
        </form>
      </div>
    </one-column>
    <footer-layout />
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Footer from '../components/common/Footer'

export default {
  components: {
    OneColumn,
    'app-header': Header,
    'footer-layout': Footer
  },
  methods: {
    testm () {
      console.log(this.$store.state.user.des)
    },
    editAccount () {
      axios.post('/api/account/edit', {
        accnt_num: this.$store.state.user.accnt_num,
        id: this.$store.state.user.id,
        name: this.$store.state.user.name,
        passwd: this.$store.state.user.passwd,
        email: this.$store.state.user.email,
        des: this.$store.state.user.des
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.my-pic {
  img {
    width: 38px;
    height: 38px;
  }
}
</style>
