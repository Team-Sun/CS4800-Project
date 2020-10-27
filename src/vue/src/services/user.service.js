import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://ec2-3-101-72-142.us-west-1.compute.amazonaws.com:8080/api/test/'

class UserService {
  getPublicContent () {
    return axios.get(API_URL + 'all')
  }

  getUserBoard () {
    return axios.get(API_URL + 'user', { headers: authHeader() })
  }

  getModeratorBoard () {
    return axios.get(API_URL + 'mod', { headers: authHeader() })
  }

  getAdminBoard () {
    return axios.get(API_URL + 'admin', { headers: authHeader() })
  }
}

export default new UserService()
