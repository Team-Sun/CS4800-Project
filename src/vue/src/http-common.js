import axios from 'axios'
import authHeader from './services/auth-token-header'

export default axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: authHeader(),
    'Content-type': 'application/json'
  }
})
