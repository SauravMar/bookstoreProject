<script setup lang="ts">
import { inject } from "vue";
import { BookItem, CategoryItem } from "@/types";
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}

.welcome-panel {
  display: flex;
  justify-content: center;
  min-width: 70%;
  padding: 2% 2%;
}

.welcome-image img {
  height: 328px !important;
}

.welcome-text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  max-width: 75%;
  padding: 0 2%;
}

.carousel-button {
  font-size: 100px;
  font-weight: bolder;
  color: var(--primary-color);
  z-index: 999;
}

.carousel-item {
  padding: 0 10%;
}

.carousel-control-next {
  padding-left: 1%;
}

.carousel-control-prev {
  padding-right: 1%;
}
</style>

<template>
  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <a
      class="carousel-control-prev carousel-button"
      href="#carouselExampleControls"
      role="button"
      data-slide="prev"
    >
      <i class="fa fa-chevron-left" aria-hidden="true"></i>
      <span class="sr-only">Previous</span>
    </a>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <div class="welcome-panel">
          <div class="welcome-image">
            <img src="../assets/images/books/introbook2.webp" height="328" />
          </div>
          <div class="welcome-text">
            <h3 style="font-weight: bolder; font-size: 32px">
              A Natural History of the Future
            </h3>
            <h5>Rob Dunn</h5>
            <h4 style="font-weight: 500">
              What the Laws of Biology Tell Us about the Destiny of the Human
              Species
            </h4>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="welcome-panel">
          <div class="welcome-image">
            <img src="../assets/images/categories/eric.webp" height="328" />
          </div>
          <div class="welcome-text">
            <h3 style="font-weight: bolder; font-size: 32px">
              Things Have Gotten Worse Since We Last Spoke And Other Misfortunes
            </h3>
            <h5>Eric LaRocca</h5>
            <h4 style="font-weight: 500">
              Three dark and disturbing horror stories from an astonishing new
              voice, including the viral-sensation tale of obsession, Things
              Have Gotten Worse Since We Last Spoke.
            </h4>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="welcome-panel">
          <div class="welcome-image">
            <img src="../assets/images/categories/death.webp" height="328" />
          </div>
          <div class="welcome-text">
            <h3 style="font-weight: bolder; font-size: 32px">
              The Death of Jane Lawrence: A Novel
            </h3>
            <h5>Rob Dunn</h5>
            <h4 style="font-weight: 500">
              From the Bram Stoker-nominated author of The Luminous Dead comes a
              gothic fantasy horror—The Death of Jane Lawrence.
            </h4>
          </div>
        </div>
      </div>
    </div>
    <a
      class="carousel-control-next carousel-button"
      href="#carouselExampleControls"
      role="button"
      data-slide="next"
    >
      <i class="fa fa-chevron-right" aria-hidden="true"></i>
      <span class="sr-only">Next</span>
    </a>
  </div>
</template>
