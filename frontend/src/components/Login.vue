<template>
  <div class="account">
      <div class="my-account">
        <div class="logo">
          <a class="icon-sprite ico-core-2 instagram-logo_account"><span>logo</span></a>
        </div>
        <div class="account-form">
          <form class="form" name="form" method="post" action="/api/login">
            <fieldset name="fieldset">
              <div class="field id">
                <label>휴대폰번호 또는 이메일 주소</label>
                <input name="userId" type="text" v-model="userId"/>
              </div>
              <div class="field passwd">
                <label>비밀번호</label>
                <input name="userPasswd" type="password" v-model="userPasswd"/>
              </div>
              <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
              <div class="field login_btn">
                <button name="login_btn" type="submit">로그인</button>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="login">
        <div class="my-account my-login">계정이 없으신가요? <a href="/account">가입하기</a></div>
      </div>
  </div>
</template>

<script>
// import axios from 'axios'
export default {
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
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

  .login {
    a {
      color: #003569;
      text-decoration: none;
      font-weight: bold;
    }
  }
}
</style>
