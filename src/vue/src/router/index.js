import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
// import Login from '../views/Login.vue'
import Search from '../views/Search.vue'
import UploadPage from '../views/Upload.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/uploadPage',
    name: 'UploadPage',
    component: UploadPage
  },
  {
    path: '/about',
    name: 'About',
    component: About
  }
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: Login
  // }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router