<script setup lang="ts"></script>

<style scoped>
/*footer {*/
/*  background: var(--secondary-background-color);*/
/*  display: flex;*/
/*  gap: 1em;*/
/*}*/

/*.links,*/
/*.social-media-icons {*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*}*/

/*.social-media-icons {*/
/*  text-align: center;*/
/*  gap: 0.25em;*/
/*}*/
footer {
  color: var(--secondary-background-color);
  display: flex;
  gap: 1em;
}

.social-media-icons {
  gap: 0.5em;
  font-size: 20px;
  width: 25px;
  text-align: center;
  text-decoration: none;
  border-radius: 40%;
  color: var(--primary-color);
  padding-left: 10%;
  display: flex;
}

.social-media-icons a {
  color: var(--primary-color) !important;
  text-decoration: none;
}

.footer-container {
  width: 100%;
  font-family: var(--default-font-family);
}

.footer-body {
  height: 80%;
  min-height: 80%;
  display: flex;
  justify-content: space-between;
  font-family: var(--default-font-family);
}

.links {
  display: flex;
  flex-direction: column;
  min-width: 15%;
  padding: 0.8% 1% 0 1%;
  font-size: 12px;
}

.links a {
  color: var(--primary-color) !important;
  text-decoration: none;
}

.social-media {
  width: 15%;
  min-width: 10%;
  padding: 1% 1% 0 1%;
}

.fa:hover {
  opacity: 0.7;
}

.follow-element {
  font-size: 14px;
  font-weight: 900;
  text-align: center;
  color: var(--primary-color);
}

.footer-tag {
  display: flex;
  justify-content: space-around;
  background: var(--primary-color);
  font-size: 12px;
  font-weight: 700;
  padding: 0.4% 0;
  font-family: var(--default-font-family);
  text-decoration: none;
}

.footer-tag a {
  color: white !important;
  font-weight: normal;
  font-size: 12px;
  text-decoration: none;
}

.newsletter {
  min-width: 50%;
  margin: 0% 10%;
}

.adv-button {
  width: 30px;
  height: 30px;
  border: 1px solid var(--primary-color);
  background: var(--primary-color);
  text-align: center;
  color: var(--secondary-background-color);
  border-radius: 0 10px 10px 0;
  cursor: pointer;
  font-size: 15px;
}

.adv-bar {
  width: 100%;
  height: 30px !important;
  border: 1.7px solid var(--primary-color);
  border-right: none;
  padding: 0% 0% 0% 3%;
  border-radius: 10px 0 0 10px;
  outline: none;
  color: var(--secondary-background-color);
  font-size: 13px;
  font-weight: 500;
}

.adv-bar:focus {
  color: #00b4cc;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
  padding: 2% 10% 2% 10%;
  font-family: var(--default-font-family);
}

.signup {
  padding-top: 2%;
}
</style>

<template>
  <footer>
    <div class="footer-container">
      <div style="height: 2%; background-color: #294b73"></div>
      <div class="footer-body">
        <div class="links">
          <section class="links">
            <router-link to="/">ABOUT US</router-link>
            <router-link to="/">SUPPORT / HELP</router-link>
            <router-link to="/">DIRECTIONS</router-link>
          </section>
        </div>
        <div class="newsletter">
          <section class="signup align-center">
            <!-- <h1 class="text-logo">Another Bookstore</h1> -->
            <form style="width: 100%">
              <div class="search">
                <input
                  type="text"
                  class="adv-bar"
                  placeholder="Sign Up for Savings, News & Updates"
                /><br />
                <button type="submit" class="adv-button">
                  <i class="fa fa-play"></i>
                </button>
              </div>
            </form>
          </section>
        </div>
        <div class="social-media">
          <div class="follow-element">FOLLOW US:</div>
          <div class="social-media-icons">
            <span><a href="#" class="fa fa-facebook"></a></span>
            <span><a href="#" class="fa fa-twitter"></a></span>
            <span><a href="#" class="fa fa-youtube"></a></span>
            <span><a href="#" class="fa fa-instagram"></a></span>
            <span><a href="#" class="fa fa-pinterest"></a></span>
          </div>
        </div>
      </div>
      <div class="footer-tag">
        <router-link to="/">Terms of Use</router-link>
        <router-link to="/">&copy; 2022 All Rights Reserved</router-link>
        <router-link to="/">Privacy Notice</router-link>
        <router-link to="/">Sitemap</router-link>
      </div>
    </div>
    <!--    <section class="links">-->
    <!--      <router-link to="/">about</router-link>-->
    <!--      <router-link to="/">contact</router-link>-->
    <!--      <router-link to="/">directions</router-link>-->
    <!--    </section>-->
    <!--    <section class="social-media-icons">-->
    <!--      <router-link to="/" class="button">Facebook</router-link>-->
    <!--      <router-link to="/" class="button">Twitter</router-link>-->
    <!--    </section>-->
  </footer>
</template>
