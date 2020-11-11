import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Register from '../views/Register.vue'
import Login from '../views/Login.vue'
import Search from '../views/Search.vue'
import UploadPage from '../views/Upload.vue'
import IndividualNote from '../views/IndividualNote.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
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
    path: '/individualNote/:id',
    name: 'IndividualNote',
    component: IndividualNote
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('../views/Profile.vue')
  },
  // Temporary
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/BoardAdmin.vue')
  },
  {
    path: '/moderator',
    name: 'Moderator',
    component: () => import('../views/BoardModerator.vue')
  },
  {
    path: '/user',
    name: 'User',
    component: () => import('../views/BoardUser.vue')
  },
  // {
  //   path: '/notes',
  //   name: 'notes',
  //   component: () => import('../components/NotesList')
  // },
  {
    path: '/note/:id',
    name: 'note-details',
    component: () => import('../components/Note')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
