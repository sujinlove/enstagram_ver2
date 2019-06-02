<template>
  <div class="account">
      <div class="my-account">
        <div class="logo">
          <a class="icon-sprite ico-core-2 instagram-logo_account"><span>logo</span></a>
        </div>
        <div class="account-form">
            <h2>친구들의 사진과 동영상을 보려면 가입하세요.</h2>
          <form class="form" name="form">
            <fieldset class="fieldset">
              <input name="accnt_num" type="hidden" v-model="accnt_num"/>{{ accnt_num }}
              <div class="field email">
                <label for="login-email">이메일 주소</label>
                <input id="login-email" name="email" type="email" v-model="email"/>
              </div>
              <div class="field name">
                <label for="login-name">성명</label>
                <input id="login-name" name="name" type="text" v-model="name"/>
              </div>
              <div class="field id">
                <label for="login-id">사용자 이름</label>
                <input id="login-id" name="id" type="text" maxlength="30" v-model="id"/>
              </div>
              <div class="field passwd">
                <label for="login-passwd">비밀번호</label>
                <input id="login-passwd" name="passwd" type="password" v-model="passwd"/>
              </div>
              <div class="field account_btn">
                <button name="account_btn" type="button" @click="addSubmit" class="action primary">가입</button>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="others">
        <p>계정이 있으신가요? <a href="/login">로그인</a></p>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      accnt_num: '',
      id: '',
      name: '',
      email: '',
      passwd: '',
      account: []
    }
  },
  watch: {
    id: function (newValue) {
      this.changeValue('id', newValue)
    },
    name: function (newValue) {
      this.changeValue('name', newValue)
    },
    email: function (newValue) {
      this.changeValue('email', newValue)
    },
    passwd: function (newValue) {
      this.changeValue('passwd', newValue)
    }
  },
  methods: {
    changeValue (inputId, value) {
      if (value !== '') {
        document.querySelector('.field.' + inputId).classList.add('active')
      } else {
        document.querySelector('.field.' + inputId).classList.remove('active')
      }
    },
    addSubmit () {
      axios.post('/api/account', {
        accnt_num: this.accnt_num,
        id: this.id,
        name: this.name,
        email: this.email,
        passwd: this.passwd
      }).then(response => {
        window.location.href = '/'
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
