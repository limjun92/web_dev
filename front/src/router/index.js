import { createWebHistory, createRouter } from "vue-router";
import Home from "../views/Home_h.vue";
import PostDetail from "../views/PostDetail.vue";
import MainCalendar from "../views/MainCalendar.vue";
import Board from "../views/Board.vue";

const routes = [
  {
    path: "/",
    name: "Post List",
    component: Home,
  },
  {
    path: "/about",
    name: "PostDetail",
    component: PostDetail,
  },
  {
    path: "/MainCalendar",
    name: "MainCalendar",
    component: MainCalendar,
  },
  {
    path: "/Board",
    name: "Board",
    component: Board,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;