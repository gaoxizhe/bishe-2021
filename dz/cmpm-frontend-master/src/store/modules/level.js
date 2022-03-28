import {curLevel} from '@/api/level'

const getDefaultState = () => {
  return {
    curLevel: '',
  }
}

const state = getDefaultState()

const mutations = {
  SET_LEVEL: (state, level) => {
    state.curLevel = level
  },
}

const actions = {
  curLevel({ commit }) {
    return new Promise((resolve, reject) => {
      curLevel().then(response => {
        const { data } = response
        commit('SET_LEVEL', data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

