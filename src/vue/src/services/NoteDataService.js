import http from '../http-common'
import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/api'

class NoteDataService {
  getAll () {
    return http.get('/note')
  }

  get (id) {
    return http.get(`/note/${id}`)
  }

  create (data) {
    return axios.post(API_URL + '/note', data, { headers: authHeader(), 'Content-type': 'application/json' })
  }

  update (id, data) {
    return http.put(`/note/${id}`, data)
  }

  delete (id) {
    return http.delete(`/note/${id}`)
  }

  deleteAll () {
    return http.delete('/note')
  }

  findByTitle (title) {
    return http.get(`/note?title=${title}`)
  }
}

export default new NoteDataService()
