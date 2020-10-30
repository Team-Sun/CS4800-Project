import http from '../http-common'
import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/api'

class NoteDataService {
  getAll () {
    return http.get('/notes')
  }

  get (id) {
    return http.get(`/notes/${id}`)
  }

  create (data) {
    return axios.post(API_URL + '/uploadPage', data, { headers: authHeader(), 'Content-type': 'application/json' })
  }

  update (id, data) {
    return http.put(`/notes/${id}`, data)
  }

  delete (id) {
    return http.delete(`/notes/${id}`)
  }

  deleteAll () {
    return http.delete('/notes')
  }

  findByTitle (title) {
    return http.get(`/notes?title=${title}`)
  }
}

export default new NoteDataService()
