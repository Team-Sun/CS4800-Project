import http from '../http-common'

class AuthService {
  login (user) {
    return http.getInstance()
      .post('/auth/signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        console.log('Logging in')
        console.log(response.data.accessToken)
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data))
          console.log('Access token set')
        }

        return response.data
      })
  }

  logout () {
    return http.getInstance().post('/auth/logout')
      .then(response => {
        return response.data
      })
  }

  register (user) {
    return http.getInstance().post('/auth/signup', {
      username: user.username,
      email: user.email,
      password: user.password
    })
  }
}

export default new AuthService()
