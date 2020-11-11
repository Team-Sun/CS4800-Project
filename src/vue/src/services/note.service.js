/* eslint-disable no-mixed-spaces-and-tabs */
import axios from 'axios'
import authHeader from './auth-header'
// import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/api/note/'

// Thankyou https://kapeli.com/cheat_sheets/Axios.docset/Contents/Resources/Documents/index
class NoteService {
  search (note) {
    return axios.post(API_URL + 'search', {
      title: note.title,
      course: note.course,
      professor: note.professor
    }).then(response => {
      return Promise.resolve(response.data) // Seems to be the same thing as without the Promise.resolve()?
    })
  }

  // Thanks https://stackoverflow.com/questions/21329426/spring-mvc-multipart-request-with-json/25183266#25183266
  upload (note) {
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
    return axios.post(API_URL + 'add', data, { headers: authHeader(), 'Content-Type': undefined }).then(response => {
      return Promise.resolve(response.data) // Seems to be the same thing as without the Promise.resolve()?
    })
  }
}

export default new NoteService()
