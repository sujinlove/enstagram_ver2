<template>
  <div class="account">
      <div class="my-account">
        <div class="logo">
          <a class="icon-sprite ico-core-2 instagram-logo_account"><span>logo</span></a>
        </div>
        <div class="account-form">
            <h2>친구들의 사진과 동영상을 보려면 가입하세요.</h2>
          <form class="form" name="form" method="post" action="/api/account">
            <fieldset name="fieldset">
              <input name="accnt_num" type="hidden" v-model="accnt_num"/>{{ accnt_num }}
              <div class="field email">
                <label>휴대폰번호 또는 이메일 주소{{ id }}</label>
                <input name="id" type="text" v-model="id"/>
              </div>
              <div class="field name">
                <label>성명{{ name }}</label>
                <input name="name" type="text" v-model="name"/>
              </div>
              <div class="field user">
                <label>사용자 이름{{ nickname }}</label>
                <input name="nickname" type="text" v-model="nickname"/>
              </div>
              <div class="field passwd">
                <label>비밀번호{{ passwd }}</label>
                <input name="passwd" type="password" v-model="passwd"/>
              </div>
              <div class="field account_btn">
                <button name="account_btn" type="button" @click="addSubmit">가입</button>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="login">
        <div class="my-account my-login">계정이 있으신가요? <a href="/login">로그인</a></div>
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
      nickname: '',
      passwd: '',
      account: []
    }
  },
  methods: {
    addSubmit () {
      // var createFormData = new FormData()
      // createFormData.append('id', this.id)
      // createFormData.append('name', this.name)
      // axios.post('/api/account', createFormData).then(response => {
      //   console.log('1: ' + this.createFormdata.id, this.createFormData.name)
      // }).catch(e => {
      //   console.log('error: ' + e)
      // })
      axios.post('/api/account', {
        accnt_num: this.accnt_num,
        id: this.id,
        name: this.name,
        nickname: this.nickname,
        passwd: this.passwd
      }).then(response => {
        console.log('1: ' + this.id, this.name)
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.account {
      width: 350px;
      display: inline-block;
      vertical-align: middle;
    .my-account {
        background-color: #fff;
        border: 1px solid #e6e6e6;
        border-radius: 1px;
        margin: 0 0 10px;
        padding: 10px 0;
        text-align: center;
        overflow: hidden;
        height: 365px;
        padding: 6px 40px;

        .logo {
          margin: 22px auto 12px;
        }

        h2 {
          font-weight: 600;
          line-height: 20px;
          font-size: 17px;
          margin: 0 0 20px;
          color: #999;
        }

        &.my-login {
          height: auto;

            a {
              color: #3897f0;
              text-decoration: none;
            }
        }
    }

  .account-form {
    height: 37px;
    .form {
      fieldset {
        input {
            background: #fafafa;
            border: 0;
            padding: 9px 0;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
            border-radius: 3px;
            border: 1px solid #efefef;
        }

        label {
          position: absolute;
          font-size: 12px;
          color: #999;
          padding: 0 8px;
          line-height: 35px;
          pointer-events: none;
        }
      }

      .field {
        margin: 0 0 6px;
      }
    }
  }
}
</style>
