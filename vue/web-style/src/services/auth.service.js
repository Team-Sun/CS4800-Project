import axios from 'axios'

const API_URL = 'http://localhost:8080/api/auth/'

class AuthService {
  // POST {username, password} & save JWT to Local Storage
  login (user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data))
        }
        return response.data
      })
  }

  // remove JWT from Local Storage
  logout () {
    localStorage.removeItem('user')
  }

  // POST {username, email, password}
  resigter (user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    })
  }
}

export default new AuthService()
