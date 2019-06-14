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
                <label for="account-email">이메일 주소</label>
                <input id="account-email" name="email" type="email" maxlength="45" v-model="email"/>
              </div>
              <div class="field name">
                <label for="account-name">성명</label>
                <input id="account-name" name="name" type="text" maxlength="30" v-model="name"/>
              </div>
              <div class="field id">
                <label for="account-id">사용자 이름</label>
                <input id="account-id" name="id" type="text" maxlength="30" v-model="id"/>
              </div>
              <div class="field passwd">
                <label for="account-passwd">비밀번호</label>
                <input id="account-passwd" name="passwd" type="password" maxlength="45" v-model="passwd"/>
              </div>
              <div class="field account_btn">
                <button name="account_btn" type="button" @click="addSubmit" class="action primary">가입</button>
              </div>
              <p class="message error">{{this.errorMsg}}</p>
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
      account: [],
      countId: 0,
      errorMsg: ''
    }
  },
  watch: {
    id: function (newValue) {
      this.checkId(newValue)
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
      if (inputId === 'id') {
        if (value.length > 3 && value.length < 31 && this.countId === 0) {
          document.querySelector('.field.' + inputId).classList.remove('error')
          document.querySelector('.field.' + inputId).classList.add('accept')
        } else {
          document.querySelector('.field.' + inputId).classList.remove('accept')
          document.querySelector('.field.' + inputId).classList.add('error')
        }
      }
      if (inputId === 'passwd') {
        if (value.length > 5 && value.length < 46) {
          document.querySelector('.field.' + inputId).classList.remove('error')
          document.querySelector('.field.' + inputId).classList.add('accept')
        } else {
          document.querySelector('.field.' + inputId).classList.remove('accept')
          document.querySelector('.field.' + inputId).classList.add('error')
        }
      }
      if (inputId === 'email') {
        var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
        if (regExp.test(value) && value.length < 46) {
          document.querySelector('.field.' + inputId).classList.remove('error')
          document.querySelector('.field.' + inputId).classList.add('accept')
        } else {
          document.querySelector('.field.' + inputId).classList.remove('accept')
          document.querySelector('.field.' + inputId).classList.add('error')
        }
      }
    },
    addSubmit () {
      var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
      if (this.id.length > 3 && this.id.length < 31 && this.countId === 0 && regExp.test(this.email) && this.email.length < 46 && this.passwd.length < 46 && this.passwd.length > 5) {
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
      } else {
        document.querySelector('.field.id').classList.add('error')
        document.querySelector('.field.passwd').classList.add('error')
        document.querySelector('.field.email').classList.add('error')
        this.errorMsg = 'This field is required.'
      }
    },
    checkId (value) {
      axios.post('/api/check/id', {
        id: value
      }).then(response => {
        this.countId = response.data
        this.changeValue('id', value)
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
