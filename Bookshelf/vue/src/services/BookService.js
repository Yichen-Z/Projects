import axios from 'axios'

const http = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export default {
    list() {
        return http.get(`/books`)
    },
    create(newBook) {
        return http.post(`/books`, newBook)
    },
    get(bookID) {
        return http.get(`books/${bookID}`)
    }
}