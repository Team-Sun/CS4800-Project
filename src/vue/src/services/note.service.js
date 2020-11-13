// import axios from 'axios'
import authHeader from './auth-header'
import http from '../http-common'
// import authHeader from './auth-header'

// const API_URL = 'http://localhost:8080/api'

// Thankyou https://kapeli.com/cheat_sheets/Axios.docset/Contents/Resources/Documents/index
class NoteService {
  // Thanks https://stackoverflow.com/questions/21329426/spring-mvc-multipart-request-with-json/25183266#25183266
  create (note) {
    var data = new FormData()
    data.append('file', note.file)
    data.append('note', new Blob([JSON.stringify({
      content: note.content,
      title: note.title,
      course: note.course,
      professor: note.professor,
      semester: note.semester,
      description: note.description
    })],
    {
      type: 'application/json'
    }))
    return http.post('/note', data, { headers: authHeader(), 'Content-Type': undefined })
  }

  getAll () {
    return http.get('/note')
  }

  getPage (n) {
    return http.get('/note', { page: n })
  }

  get (id) {
    return http.get(`/note/${id}`)
  }

  // TODO FIX AS WELL
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

export default new NoteService()
