import axios from 'axios'
import authHeader from './auth-header'
import http from '../http-common'

const API_URL = 'http://localhost:8080/api/test/'

class UserService {
  getPublicContent () {
    return axios.get(API_URL + 'all')
  }

  updateUser (id, user) {
    return http.getInstance().post(`/user/update/${id}`, user, { headers: authHeader() }).then(response => {
      return response.data
    })
  }

  deleteUser (id) {
    return http.getInstance().delete(`/user/delete/${id}`)
      .then(response => {
        console.log(response.status)
        if (response.status === 401) {
          return Promise.reject(response.data)
        } else if (response.status === 200) {
          return Promise.resolve()
        }
      })
  }

  // TODO change to point to actual data usable by these users.
  getModeratorBoard () {
    return axios.get(API_URL + 'mod', { headers: authHeader() })
  }

  getAdminBoard () {
    return axios.get(API_URL + 'admin', { headers: authHeader() })
  }

  getUsername (id) {
    return http.getInstance().get(`/user/${id}`)
  }
}

export default new UserService()
