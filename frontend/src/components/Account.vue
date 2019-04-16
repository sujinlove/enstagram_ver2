<template>
  <div class="account">
    <div class="account-container">
      <div class="my-account">
        <div class="logo">
          <a class="icon-sprite ico-core instagram-logo_account"><span>instagram-logo_account</span></a>
        </div>
        <div class="account-form">
            <h2>친구들의 사진과 동영상을 보려면 가입하세요.</h2>
          <form class="form" name="form">
            <fieldset name="fieldset">
              <div class="field email">
                <label>휴대폰번호 또는 이메일 주소</label>
                <input name="email" type="tel"/>
              </div>
              <div class="field name">
                <label>성명</label>
                <input name="name" type="text"/>
              </div>
              <div class="field user">
                <label>사용자 이름</label>
                <input name="user" type="text"/>
              </div>
              <div class="field passwd">
                <label>비밀번호</label>
                <input name="passwd" type="password"/>
              </div>
              <div class="field passwd">
                <button name="account_btn">가입</button>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      <!-- <div class="my-count">
        <ul>
          <li>
            <span>게시물</span>
            <span class="feed-count">1</span>
          </li>
          <li>
            <span>팔로워</span>
            <span class="follower-count">1</span>
          </li>
          <li>
            <span>팔로우</span>
            <span class="follow-count">1</span>
          </li>
        </ul>
      </div>
      <div class="my-des">vue~~~~~enstagram~~</div> -->
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.account {
  .account-container {
      height: 564px;
      width: 350px;
    .my-account {
        background-color: #fff;
        border: 1px solid #e6e6e6;
        border-radius: 1px;
        margin: 0 0 10px;
        padding: 10px 0;
        text-align: center;
        overflow: hidden;
        height: 100%;
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
