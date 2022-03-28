const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  username: state => state.user.username,
  name: state => state.user.name,
  team: state => state.user.team,
  curLevel: state => state.level.curLevel
}
export default getters
