<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<template>
  <section>
    <one-column>
      <div class="phone-container" slot="main">
        <img src="https://www.instagram.com/static/images/homepage/home-phones.png/43cc71bb1b43.png" alt=""/>
        <!-- <div class="img-slider" id="imgSlider">
          <img :src="images[currentNumber % images.length]" />
        </div> -->
      </div>
      <account slot="main"></account>
    </one-column>
  </section>
</template>

<script>
import OneColumn from '../components/common/OneColumn'
import Account from '../components/Account'

export default {
  components: {
    OneColumn,
    Account
  }
//
//     el: '#imgSlider',
//     data: {
//       images: [
//                'https://www.instagram.com/static/images/homepage/screenshot1.jpg/d6bf0c928b5a.jpg',
//                'https://www.instagram.com/static/images/homepage/screenshot2.jpg/6f03eb85463c.jpg',
//                'https://www.instagram.com/static/images/homepage/screenshot3.jpg/f0c687aa6ec2.jpg',
//                'https://www.instagram.com/static/images/homepage/screenshot4.jpg/842fe5699220.jpg',
//                'https://www.instagram.com/static/images/homepage/screenshot5.jpg/0a2d3016f375.jpg'
//              ],
//       currentNumber: 0,
//       timer: null
//     },
//
//     ready: function () {
//         this.startRotation();
//     },
//
//     methods: {
//       startRotation: function() {
//       this.timer = setInterval(this.next, 3000);
//       },
//
//       stopRotation: function() {
//           clearTimeout(this.timer);
//           this.timer = null;
//       },
//
//       next: function() {
//           this.currentNumber += 1
//       }
//     }
 }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.phone-container {
    width: 50%;
    display: inline-block;
    vertical-align: middle;
}
</style>
