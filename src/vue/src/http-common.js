import axios from 'axios'
import authHeader from './services/auth-token-header'

// Had to be changed to this, which is also most definitely not correct. Too late to change for potential final deployment though.
// TODO LOOKAT and fix
const http = {
  getInstance () {
    return axios.create({
      baseURL: '/api',
      headers: {
        Authorization: authHeader(),
        'Content-Type': 'application/json'
      }
    })
  }
}
export default http
