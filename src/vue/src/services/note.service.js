import axios from 'axios'
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
}

export default new NoteService()
